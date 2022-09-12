package myproject.connectFirebase.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import myproject.connectFirebase.model.entity.Student;

@Service
public class studentService {
    
    private static final String COLLECTION_NAME = "student";
    

    public String save(Student student) throws InterruptedException, ExecutionException{
        Firestore dbfirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture= dbfirestore.collection(COLLECTION_NAME).document(student.getName()).set(student);

        return collectionApiFuture.get().getUpdateTime().toString() ;
    }

    public Student getStudent(String name) throws InterruptedException, ExecutionException{
        Firestore dbfirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference=dbfirestore.collection(COLLECTION_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot doc = future.get();

        Student student = null;
        
        if(doc.exists()){
            student = doc.toObject(Student.class);
        }

        return student;
    }

    public String update(Student student) throws InterruptedException, ExecutionException{
        Firestore dbfirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture= dbfirestore.collection(COLLECTION_NAME).document(student.getName()).set(student);

        return collectionApiFuture.get().getUpdateTime().toString() ;
    }

    public String delete(String name) throws InterruptedException, ExecutionException{
        Firestore dbfirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture= dbfirestore.collection(COLLECTION_NAME).document(name).delete();

        return "Data with name : " + name + "Has been Deleted";
    }

    public List<Student> getStudentAll() throws InterruptedException, ExecutionException{
        Firestore dbfirestore = FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentReference=dbfirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Student> studentList=new ArrayList<>();
        Student student = null;

        while(iterator.hasNext()){
            DocumentReference documentReference1=iterator.next();
            ApiFuture<DocumentSnapshot> future=documentReference1.get();
            DocumentSnapshot doc = future.get();
           student = doc.toObject(Student.class);
           studentList.add(student);
        }
        return studentList;
    }

}
