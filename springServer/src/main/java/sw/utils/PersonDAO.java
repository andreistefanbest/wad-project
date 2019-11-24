package sw.utils;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sw.user.dto.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDAO {

    public List<PersonDTO> read() throws Exception {
        List<PersonDTO> persons = new ArrayList<>();

        addFacebookDetails(persons);
        addEuropassDetails(persons);
        addTranscriptDetails(persons);

        return persons;
    }

    public Integer numOfTechSubjects(String mail) throws Exception{
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();

        Document doc = builder.parse(ClassLoader.getSystemResourceAsStream("transcript.xml"));
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        XPathExpression expr = xpath.compile("count(/persons/person[mail='" + mail + "']/formal-background/schools/school/subjects/subject/technology-like)");
        Double result = (Double) expr.evaluate(doc, XPathConstants.NUMBER);

        return result.intValue();
    }

    private void addTranscriptDetails(List<PersonDTO> persons) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =
                builder.parse(ClassLoader.getSystemResourceAsStream("transcript.xml"));
        document.getDocumentElement().normalize();

        NodeList personsList = document.getElementsByTagName("person");
        for (int i = 0; i < personsList.getLength(); i++) {
            var person = persons.get(i);
            var ePerson = (Element) personsList.item(i);
            person.setSchools(fetchSchools(ePerson));
            person.setExtraCourses(fetchCourses(ePerson, person.getEmail()));
            person.setAwards(fetchAwards(ePerson));
        }
    }

    private void addEuropassDetails(List<PersonDTO> persons) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =
                builder.parse(ClassLoader.getSystemResourceAsStream("europass.xml"));
        document.getDocumentElement().normalize();

        NodeList personsList = document.getElementsByTagName("person");
        for (int i = 0; i < personsList.getLength(); i++) {
            var person = persons.get(i);
            var ePerson = (Element) personsList.item(i);
            person.setMemberships(fetchMemberships(ePerson));
            person.setJobs(fetchJobs(ePerson));
            person.setInterests(fetchInterests(ePerson));
        }
    }

    private void addFacebookDetails(List<PersonDTO> persons) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =
                builder.parse(ClassLoader.getSystemResourceAsStream("facebook.xml"));
        document.getDocumentElement().normalize();

        NodeList personsList = document.getElementsByTagName("person");
        for (int i = 0; i < personsList.getLength(); i++) {
            var person = new PersonDTO();
            var ePerson = (Element) personsList.item(i);
            person.setName(ePerson.getElementsByTagName("name").item(0).getTextContent());
            person.setGender(ePerson.getElementsByTagName("gender").item(0).getTextContent());
            person.setWebsite(ePerson.getElementsByTagName("website").item(0).getTextContent());
            person.setAddress(ePerson.getElementsByTagName("address").item(0).getTextContent());
            person.setTelephone(ePerson.getElementsByTagName("telephone").item(0).getTextContent());
            person.setEmail(ePerson.getElementsByTagName("email").item(0).getTextContent());
            person.setIsInterestedInProgramming(isInterestedInProgramming(person.getEmail()));

            persons.add(person);
        }
    }

    private Boolean isInterestedInProgramming(String email) throws Exception {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();

        Document doc = builder.parse(ClassLoader.getSystemResourceAsStream("europass.xml"));
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        XPathExpression expr = xpath.compile("count(/persons/person[mail='" + email + "']/interests[interest='programming'])>0");
        Boolean result = (Boolean) expr.evaluate(doc, XPathConstants.BOOLEAN);

        return result;
    }

    private List<String> fetchInterests(Element ePerson) {
        var interests = new ArrayList<String>();
        var eInterests = ePerson.getElementsByTagName("interest");
        for (int j = 0; j < eInterests.getLength(); j++) {
            interests.add(eInterests.item(j).getTextContent());
        }

        return interests;
    }

    private List<JobDTO> fetchJobs(Element ePerson) {
        var jobs = new ArrayList<JobDTO>();
        var eJobs = ePerson.getElementsByTagName("job");
        for (int j = 0; j < eJobs.getLength(); j++) {
            var membership = new JobDTO();
            var eMembership = (Element) eJobs.item(j);
            membership.setTitle(eMembership.getElementsByTagName("title").item(0).getTextContent());
            membership.setDescription(eMembership.getElementsByTagName("description").item(0).getTextContent());
            membership.setPeriod(eMembership.getElementsByTagName("period").item(0).getTextContent());
            jobs.add(membership);
        }

        return jobs;
    }

    private List<MembershipDTO> fetchMemberships(Element ePerson) {
        var memberships = new ArrayList<MembershipDTO>();
        var eMemberships = ePerson.getElementsByTagName("membership");
        for (int j = 0; j < eMemberships.getLength(); j++) {
            var membership = new MembershipDTO();
            var eMembership = (Element) eMemberships.item(j);
            membership.setTitle(eMembership.getElementsByTagName("title").item(0).getTextContent());
            membership.setDescription(eMembership.getElementsByTagName("description").item(0).getTextContent());
            membership.setPeriod(eMembership.getElementsByTagName("period").item(0).getTextContent());
            memberships.add(membership);
        }

        return memberships;
    }

    private List<AwardDTO> fetchAwards(Element ePerson) {
        var awards = new ArrayList<AwardDTO>();
        var eAwards = ePerson.getElementsByTagName("award");
        for (int j = 0; j < eAwards.getLength(); j++) {
            var award = new AwardDTO();
            var eAward = (Element) eAwards.item(j);
            award.setTitle(eAward.getElementsByTagName("title").item(0).getTextContent());
            award.setYear(eAward.getElementsByTagName("year").item(0).getTextContent());
            awards.add(award);
        }

        return awards;
    }

    private List<CourseDTO> fetchCourses(Element ePerson, String mail) throws Exception {
        var courses = new ArrayList<CourseDTO>();
        var eCourses = ePerson.getElementsByTagName("course");
        for (int j = 0; j < eCourses.getLength(); j++) {
            var course = new CourseDTO();
            var eCourse = (Element) eCourses.item(j);
            String courseName = eCourse.getElementsByTagName("name").item(0).getTextContent();
            course.setName(courseName);
            course.setYear(eCourse.getElementsByTagName("year").item(0).getTextContent());
            course.setIsInLastThreeYears(isInLastThreeYears(courseName, mail));
            courses.add(course);
        }

        return courses;
    }

    private Boolean isInLastThreeYears(String courseName,String mail) throws Exception {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();

        Document doc = builder.parse(ClassLoader.getSystemResourceAsStream("transcript.xml"));
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        XPathExpression expr = xpath.compile("count(/persons/person[mail='" + mail + "']/informal-background/extra-courses/course[name ='" + courseName + "'][year > 2015])>0");
        Boolean result = (Boolean) expr.evaluate(doc, XPathConstants.BOOLEAN);

        return result;
    }

    private List<SchoolDTO> fetchSchools(Element ePerson) {
        var schools = new ArrayList<SchoolDTO>();
        var eSchools = ePerson.getElementsByTagName("school");
        for (int j = 0; j < eSchools.getLength(); j++) {
            var school = new SchoolDTO();
            var eSchool = (Element) eSchools.item(j);
            school.setName(eSchool.getElementsByTagName("name").item(0).getTextContent());
            school.setYear(eSchool.getElementsByTagName("graduation-year").item(0).getTextContent());

            var subjects = new ArrayList<SubjectDTO>();
            var eSubjects = eSchool.getElementsByTagName("subject");
            for (int k = 0; k < eSubjects.getLength(); k++) {
                var subject = new SubjectDTO();
                var eSubject = (Element) eSubjects.item(k);

                subject.setName(eSubject.getElementsByTagName("name").item(0).getTextContent());
                subject.setKeyword(eSubject.getElementsByTagName("keywords").item(0).getTextContent());
                subject.setYear(eSubject.getElementsByTagName("year").item(0).getTextContent());
                subject.setGrade(eSubject.getElementsByTagName("grade").item(0).getTextContent());

                Node technologyLike = eSubject.getElementsByTagName("technology-like").item(0);
                if (technologyLike != null) {
                    subject.setIsTech(Boolean.parseBoolean(technologyLike.getTextContent()));
                }

                subjects.add(subject);
            }

            school.setSubjects(subjects);
            schools.add(school);
        }

        return schools;
    }

    public Integer numOfInterestedInProgramming() throws Exception{
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();

        Document doc = builder.parse(ClassLoader.getSystemResourceAsStream("europass.xml"));
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        XPathExpression expr = xpath.compile("count(/persons/person/interests[interest='programming'])");
        Double result = (Double) expr.evaluate(doc, XPathConstants.NUMBER);

        return result.intValue();
    }

    public void deleteJob(String mail, String jobTitle) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =
                builder.parse(ClassLoader.getSystemResourceAsStream("europass.xml"));
        document.getDocumentElement().normalize();

        NodeList personsList = document.getElementsByTagName("person");
        for (int i = 0; i < personsList.getLength(); i++) {
            var ePerson = (Element) personsList.item(i);
            if (!ePerson.getElementsByTagName("mail").item(0).getTextContent().equals(mail)) {
                continue;
            }

            Node jobs = ePerson.getElementsByTagName("jobs").item(0);
            NodeList eJobs = ePerson.getElementsByTagName("job");
            for (int j = 0; j < eJobs.getLength(); j++) {
                var eJob = (Element) eJobs.item(j);
                if (!eJob.getElementsByTagName("title").item(0).getTextContent().equals(jobTitle)) {
                    continue;
                }
                jobs.removeChild(eJob);
            }
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(ClassLoader.getSystemResource("europass.xml").toURI()));
        Source input = new DOMSource(document);

        transformer.transform(input, output);
    }

    public void addJob(String mail, JobDTO dto) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =
                builder.parse(ClassLoader.getSystemResourceAsStream("europass.xml"));
        document.getDocumentElement().normalize();

        NodeList personsList = document.getElementsByTagName("person");
        for (int i = 0; i < personsList.getLength(); i++) {
            var ePerson = (Element) personsList.item(i);
            if (!ePerson.getElementsByTagName("mail").item(0).getTextContent().equals(mail)) {
                continue;
            }

            Node eJobs = ePerson.getElementsByTagName("jobs").item(0);

            Element eJob = document.createElement("job");
            Element eTitle = document.createElement("title");
            eTitle.setTextContent(dto.getTitle());
            eJob.appendChild(eTitle);

            Element eDescription = document.createElement("description");
            eDescription.setTextContent(dto.getDescription());
            eJob.appendChild(eDescription);

            Element ePeriod = document.createElement("period");
            ePeriod.setTextContent(dto.getPeriod());
            eJob.appendChild(ePeriod);

            eJobs.appendChild(eJob);

            break;
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(ClassLoader.getSystemResource("europass.xml").toURI()));
        Source input = new DOMSource(document);

        transformer.transform(input, output);
    }
}
