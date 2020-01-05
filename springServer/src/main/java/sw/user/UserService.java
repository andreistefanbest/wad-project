
package sw.user;

import com.hp.hpl.jena.rdf.model.Model;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sw.user.dto.FileUploadDTO;
import sw.user.dto.JobDTO;
import sw.user.dto.PersonDTO;
import sw.user.entities.User;
import sw.user.entities.UserRepository;
import sw.utils.GenericService;
import sw.utils.PersonDAO;
import sw.utils.StringHasher;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

        Path rdfPath = Path.of("uploads", dto.getFileName());
        Files.createDirectories(rdfPath.getParent());
        Files.write(rdfPath, dto.getFileContent());

        readRDFAsGraph(rdfPath);
    }

    private void readRDFAsGraph(Path path) {
        Model model = FileManager.get().loadModel(path.toString());
        Graph<RDFNode, Statement> g = new JenaJungGraph(model);

        Layout<RDFNode, Statement> layout = new FRLayout(g);
        layout.setSize(new Dimension(300, 300));
        BasicVisualizationServer<RDFNode, Statement> viz =
                new BasicVisualizationServer<>(layout);
        RenderContext context = viz.getRenderContext();
        context.setEdgeLabelTransformer(Transformers.EDGE);
        context.setVertexLabelTransformer(Transformers.NODE);
        viz.setPreferredSize(new Dimension(700, 700));
        JFrame frame = new JFrame("Jena Graph");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.getContentPane().add(viz);
        frame.pack();
        frame.setVisible(true);
    }
}
