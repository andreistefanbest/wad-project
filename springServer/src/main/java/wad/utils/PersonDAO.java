package wad.utils;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import wad.user.dto.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDAO {

    public List<PersonDTO> read() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =
                builder.parse(ClassLoader.getSystemResourceAsStream("persons.xml"));
        document.getDocumentElement().normalize();
        List<PersonDTO> persons = new ArrayList<>();

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

            person.setSchools(fetchSchools(ePerson));
            person.setExtraCourses(fetchCourses(ePerson));
            person.setAwards(fetchAwards(ePerson));
            person.setMemberships(fetchMemberships(ePerson));
            person.setJobs(fetchJobs(ePerson));
            person.setInterests(fetchInterests(ePerson));

            persons.add(person);
        }


        return persons;
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
        var memberships = new ArrayList<JobDTO>();
        var eMemberships = ePerson.getElementsByTagName("job");
        for (int j = 0; j < eMemberships.getLength(); j++) {
            var membership = new JobDTO();
            var eMembership = (Element) eMemberships.item(j);
            membership.setTitle(eMembership.getElementsByTagName("title").item(0).getTextContent());
            membership.setDescription(eMembership.getElementsByTagName("description").item(0).getTextContent());
            membership.setPeriod(eMembership.getElementsByTagName("period").item(0).getTextContent());
            memberships.add(membership);
        }

        return memberships;
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

    private List<CourseDTO> fetchCourses(Element ePerson) {
        var courses = new ArrayList<CourseDTO>();
        var eCourses = ePerson.getElementsByTagName("course");
        for (int j = 0; j < eCourses.getLength(); j++) {
            var course = new CourseDTO();
            var eCourse = (Element) eCourses.item(j);
            course.setName(eCourse.getElementsByTagName("name").item(0).getTextContent());
            course.setYear(eCourse.getElementsByTagName("year").item(0).getTextContent());
            courses.add(course);
        }

        return courses;
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

                subjects.add(subject);
            }

            school.setSubjects(subjects);
            schools.add(school);
        }

        return schools;
    }
}
