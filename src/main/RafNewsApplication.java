package main;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import repositories.category.CategoryRepository;
import repositories.category.MySQLCategoryRepository;
import repositories.comment.CommentRepository;
import repositories.comment.MySQLCommentRepository;
import repositories.news.MySQLNewsRepository;
import repositories.news.NewsRepository;
import repositories.tag.MySQLTagRepository;
import repositories.tag.TagRepository;
import repositories.user.MySQLUserRepository;
import repositories.user.UserRepository;
import services.CategoryService;
import services.CommentService;
import services.NewsService;
import services.TagService;
import services.UserService;

@ApplicationPath("/api")
public class RafNewsApplication extends ResourceConfig {
	
    public RafNewsApplication() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);


        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySQLUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySQLNewsRepository.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(MySQLCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySQLCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySQLTagRepository.class).to(TagRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class);
                this.bindAsContract(NewsService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(TagService.class);
            }
        };
        register(binder);

        packages("resources");
    }
}
