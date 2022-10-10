import modelos.AlumnoModel;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<AlumnoModel> listaAlumnos = new ArrayList<>();
        int opcion = 0;
        try {
            do {
                 opcion= menu(sc);
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        inscribirAlumno(sc, listaAlumnos);
                        break;
                    case 2:
                        addAlumnosFichero(sc, listaAlumnos);
                        break;
                    case 3: addAlumnosLista(listaAlumnos);

                        break;
                    case 4:
                        System.out.println("Hasta la próxima!");
                        break;
                    default:
                }
            }while(opcion!=4);

        }catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addAlumnosLista(ArrayList<AlumnoModel> listaAlumnos) throws ParserConfigurationException, IOException, SAXException {
    listaAlumnos.clear();
        File ficheroEmpleadosXML = new File("alumno.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(ficheroEmpleadosXML);

        document.getDocumentElement().normalize();

        NodeList nodos = document.getElementsByTagName("alumno");

        for (int i = 0; i < nodos.getLength(); i++) {

            Node nodo = nodos.item(i);
            if(nodo.getNodeType() == Node.ELEMENT_NODE){
                Element alumno = (Element) nodo;
                String id = alumno.getAttribute("id_estudiante");
                String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();
                String apellidos = alumno.getElementsByTagName("apellidos").item(0).getTextContent();
                int asignaturas = Integer.parseInt(alumno.getElementsByTagName("asignaturas_matriculadas").item(0).getTextContent());

                AlumnoModel am = new AlumnoModel(id,nombre,apellidos,asignaturas);
                listaAlumnos.add(am);

            }


        }
        for (AlumnoModel lista: listaAlumnos) {
            System.out.println(lista);
        }
    }

    private static void addAlumnosFichero(Scanner sc, ArrayList<AlumnoModel> listaAlumnos) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Element raiz = document.createElement("alumnos");
        document.appendChild(raiz);




        for (int i = 0; i < listaAlumnos.size(); i++) {
            Element alumno1 = document.createElement("alumno");
            raiz.appendChild(alumno1);
            Attr id1 = document.createAttribute("id_estudiante");
            id1.setValue(listaAlumnos.get(i).getId());
            alumno1.setAttributeNode(id1);

            Element nombre1 = document.createElement("nombre");
            alumno1.appendChild(nombre1);
            nombre1.setTextContent(listaAlumnos.get(i).getNombre());

            Element apellidos1= document.createElement("apellidos");
            alumno1.appendChild(apellidos1);
            apellidos1.setTextContent(listaAlumnos.get(i).getApellidos());

            Element asignatura1 = document.createElement("asignaturas_matriculadas");
            alumno1.appendChild(asignatura1);
            asignatura1.setTextContent(String.valueOf(listaAlumnos.get(i).getAsignaturas_matriculadas()));

        }



        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer optimus = tf.newTransformer();
        DOMSource ds = new DOMSource(document);

        optimus.setOutputProperty(OutputKeys.INDENT,"yes");
        StreamResult result = new StreamResult(new File("alumno.xml"));
        optimus.transform(ds,result);

    }

    private static void inscribirAlumno(Scanner sc, ArrayList<AlumnoModel> listaAlumnos) {
        System.out.println("Dime el id");
        String id = sc.nextLine();
        System.out.println("Dime el nombre");
        String nombre = sc.nextLine();
        System.out.println("Dime el apellido");
        String apellidos = sc.nextLine();
        System.out.println("Dime las asignaturas matriculadas");
        int asignaturas = sc.nextInt();
        AlumnoModel alumnoModel = new AlumnoModel(id, nombre,apellidos,asignaturas);
        listaAlumnos.add(alumnoModel);

    }

    private static int menu(Scanner sc) {
        System.out.println("Elige una opción");
        System.out.println("1-Inscribir alumno");
        System.out.println("2-Añadir alumnos al fichero");
        System.out.println("3-Leer alumnos a la lista");
        System.out.println("4-Salir");
        return sc.nextInt();
    }


}
