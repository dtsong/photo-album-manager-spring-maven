package com.teradata.resource;

import com.teradata.controller.PhotoController;
import com.teradata.model.Photo;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class PhotoResource extends ResourceSupport {

    private final Photo photo;

    public PhotoResource(Photo photo) {
        String albumTitle = photo.getAlbum().getTitle();
        this.photo = photo;
        this.add(new Link(photo.getUrl(), "photo-url"));
        this.add(linkTo(PhotoController.class, albumTitle).withRel("photos"));
        this.add(linkTo(methodOn(PhotoController.class, albumTitle)
                        .readPhoto(albumTitle, photo.getId())).withSelfRel());
    }
}
