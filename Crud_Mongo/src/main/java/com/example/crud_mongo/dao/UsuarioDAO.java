package com.example.crud_mongo.dao;

import com.example.crud_mongo.MongoConfig;
import com.example.crud_mongo.model.Usuario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private MongoCollection<Document> collection;

    public UsuarioDAO() {
        MongoDatabase db = MongoConfig.getDatabase();
        collection = db.getCollection("usuarios");
    }

    public void crearUsuario(Usuario usuario) {
        Document doc = new Document("nombre", usuario.getNombre())
                .append("edad", usuario.getEdad());
        collection.insertOne(doc);
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        for (Document doc : collection.find()) {
            Usuario u = new Usuario(
                    doc.getObjectId("_id"),
                    doc.getString("nombre"),
                    doc.getInteger("edad"));
            usuarios.add(u);
        }
        return usuarios;
    }

    public void actualizarUsuario(Usuario usuario) {
        Document query = new Document("_id", usuario.getId());
        Document update = new Document("$set",
                new Document("nombre", usuario.getNombre()).append("edad", usuario.getEdad()));
        collection.updateOne(query, update);
    }

    public void eliminarUsuario(ObjectId id) {
        collection.deleteOne(new Document("_id", id));
    }
}
