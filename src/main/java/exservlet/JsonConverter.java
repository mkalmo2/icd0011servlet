package exservlet;

import exservlet.model.Post;

import java.util.Arrays;

public class JsonConverter {

    public static void main(String[] args) throws Exception {

        Post post = new Post();
        post.setId(1L);
        post.setTitle("Covert Json");
        post.setHidden(false);
        post.setTags(Arrays.asList("java", "json"));

    }


}
