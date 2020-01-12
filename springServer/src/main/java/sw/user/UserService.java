
package sw.user;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.RenderContext;
import net.rootdev.jenajung.JenaJungGraph;
import net.rootdev.jenajung.Transformers;
import org.apache.commons.collections15.IteratorUtils;
import org.apache.commons.collections15.Transformer;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sw.user.dto.FileUploadDTO;
import sw.user.dto.GradAndMostInterestsDTO;
import sw.user.dto.JobDTO;
import sw.user.dto.PersonDTO;
import sw.user.entities.User;
import sw.user.entities.UserRepository;
import sw.utils.GenericService;
import sw.utils.PersonDAO;
import sw.utils.StringHasher;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Component()
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringHasher stringHasher;

    @Autowired
    private GenericService genericService;

    @Autowired
    private PersonDAO personDAO;

    public User signIn(String name, String mail, String password) throws Exception {
        var user = new User();
        user.setFullName(name);
        user.setMail(mail);
        user.setPassword(stringHasher.getHash(password));
        user.setUserType("basic");

        return userRepository.save(user);
    }

    public User logIn(String mail, String password) throws Exception {
        for (var user : userRepository.findAll()) {
            if (matchMailAndPassword(mail, password, user)) {
                return user;
            }
        }

        return null;
    }

    private boolean matchMailAndPassword(String mail, String password, User user) throws Exception {
        return user.getMail().equals(mail) &&
                user.getPassword().equals(stringHasher.getHash(password));
    }

    public User getUser(Integer id) throws Exception {
        return userRepository.findById(id).orElse(null);
    }


    public List<PersonDTO> fetchPersons() throws Exception {
        return personDAO.read();
    }

    public Integer numOfTechSubjects(String mail) throws Exception {
        return personDAO.numOfTechSubjects(mail);
    }

    public Integer numOfInterestedInProgramming() throws Exception {
        return personDAO.numOfInterestedInProgramming();
    }

    public void deleteJob(String mail, String jobTitle) throws Exception {
        personDAO.deleteJob(mail, jobTitle);
    }

    public void addJob(String mail, JobDTO dto) throws Exception {
        personDAO.addJob(mail, dto);
    }


    public void uploadRDFFileAndReadAsGraph(FileUploadDTO dto) throws IOException {
        uploadRDFFileAndReadAsGraph(dto, null);
    }

    public void uploadRDFFileAndReadAsGraph(FileUploadDTO dto, String profession) throws IOException {
        Path rdfPath = Path.of("uploads", dto.getFileName());
        Files.createDirectories(rdfPath.getParent());
        Files.write(rdfPath, dto.getFileContent());

        readRDFAsGraph(rdfPath, profession);
    }

    private void readRDFAsGraph(Path path, String profession) {
        com.hp.hpl.jena.rdf.model.Model model = FileManager.get().loadModel(path.toString());
        Graph<RDFNode, Statement> g = new JenaJungGraph(model);

        Layout<RDFNode, Statement> layout = new FRLayout(g);
        layout.setSize(new Dimension(700, 700));
        BasicVisualizationServer<RDFNode, Statement> viz =
                new BasicVisualizationServer<>(layout);
        RenderContext context = viz.getRenderContext();
        context.setEdgeLabelTransformer(Transformers.EDGE);
        context.setVertexLabelTransformer(Transformers.NODE);
        viz.setPreferredSize(new Dimension(700, 700));

        if (profession != null) {
            Transformer<RDFNode,Paint> vertexColor = node -> {
                com.hp.hpl.jena.rdf.model.Property property = com.hp.hpl.jena.rdf.model.ResourceFactory.createProperty("http://example.org#", "job");
                if (node.isResource() && node.asResource().getProperty(property).getString().equals(profession)) {
                    return Color.GREEN;
                }
                return Color.RED;
            };
            viz.getRenderContext().setVertexFillPaintTransformer(vertexColor);
        }

        JFrame frame = new JFrame("Jena Graph");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.getContentPane().add(viz);
        frame.pack();
        frame.setVisible(true);
    }

    public void addEditCompany(String who, String newCompany) throws URISyntaxException, FileNotFoundException {
        Model model = ModelFactory.createDefaultModel();
        model.read(ClassLoader.getSystemResourceAsStream("rdf.rdf"), "http://example.org#");

        Resource resource = model.getResource("http://example.org#" + who);

        Property companyProperty = ResourceFactory.createProperty("http://example.org#", "company");
        if (resource.hasProperty(companyProperty)) {
            resource.getProperty(companyProperty).changeObject(newCompany);
        } else {
            resource.addProperty(companyProperty, newCompany);
        }

        model.write(new FileOutputStream(new File(ClassLoader.getSystemResource("rdf.rdf").toURI())));
    }

    public List<String> getPersonsWithProfession(String profession) throws IOException {
        List<String> result = new ArrayList<>();
        Model model = ModelFactory.createDefaultModel();
        model.read(ClassLoader.getSystemResourceAsStream("rdf.rdf"), "http://example.org#");

        Property property = ResourceFactory.createProperty("http://example.org#", "job");
        model.listResourcesWithProperty(property, profession).forEachRemaining(resource -> {
            result.add(resource.getLocalName());
        });

        uploadRDFFileAndReadAsGraph(new FileUploadDTO("test1.rdf",
                ClassLoader.getSystemResourceAsStream("rdf.rdf").readAllBytes()), profession);
        return result;
    }


    public GradAndMostInterestsDTO getOtherInfo() {
        GradAndMostInterestsDTO dto = new GradAndMostInterestsDTO();

        Model model = ModelFactory.createDefaultModel();
        model.read(ClassLoader.getSystemResourceAsStream("rdf.rdf"), "http://example.org#");

        dto.setGraduated(getGraduates(model));
        dto.setInterests(getInterests(model));
        return dto;
    }

    private List<String> getGraduates(Model model) {
        List<String> result = new ArrayList<>();

        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.setNsPrefix("cd", "http://example.org#");
        queryStr.append("PREFIX cd: <http://example.org#>\n" +
                "SELECT ?a ?p\n" +
                " WHERE {\n" +
                "\n" +
                "        ?a cd:gradyear ?p . FILTER ( ?p >= \"2015\" )         \n" +
                "}");


        Query q = queryStr.asQuery();
        ResultSet results = QueryExecutionFactory.create(q, model).execSelect() ;
        while (results.hasNext()){
            QuerySolution soln = results.nextSolution() ;
            result.add(soln.get("a").asResource().getLocalName());
        }

        return result;
    }

    private List<String> getInterests(Model model) {
        List<String> result = new ArrayList<>();

        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.setNsPrefix("cd", "http://example.org#");
        queryStr.append("PREFIX cd: <http://example.org#>\n" +
                "PREFIX cd: <http://example.org#>\n" +
                "SELECT ?a ?p\n" +
                "WHERE { ?a cd:interest ?p }    \n" +
                "ORDER BY ?p");


        Query q = queryStr.asQuery();
        ResultSet results = QueryExecutionFactory.create(q, model).execSelect() ;
        while (results.hasNext()){
            QuerySolution soln = results.nextSolution();
            result.add(soln.get("a").asResource().getLocalName());
            if (result.size() >= 3) {
                break;
            }
        }

        return result;
    }
}
