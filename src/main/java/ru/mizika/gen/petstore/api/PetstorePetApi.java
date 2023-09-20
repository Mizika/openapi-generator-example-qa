package ru.mizika.gen.petstore.api;

import java.io.File;

import ru.mizika.gen.petstore.model.ModelApiResponse;
import ru.mizika.gen.petstore.model.Pet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import ru.mizika.gen.utils.Oper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.restassured.http.Method.*;

public class PetstorePetApi {

    private Supplier<RequestSpecBuilder> reqSpecSupplier;
    private Consumer<RequestSpecBuilder> reqSpecCustomizer;

    private PetstorePetApi(Supplier<RequestSpecBuilder> reqSpecSupplier) {
        this.reqSpecSupplier = reqSpecSupplier;
    }

    public static PetstorePetApi pet(Supplier<RequestSpecBuilder> reqSpecSupplier) {
        return new PetstorePetApi(reqSpecSupplier);
    }

    private RequestSpecBuilder createReqSpec() {
        RequestSpecBuilder reqSpec = reqSpecSupplier.get();
        if (reqSpecCustomizer != null) {
            reqSpecCustomizer.accept(reqSpec);
        }
        return reqSpec;
    }

    public List<Oper> getAllOperations() {
        return Arrays.asList(
                addPet(),
                deletePet(),
                findPetsByStatus(),
                findPetsByTags(),
                getPetById(),
                updatePet(),
                updatePetWithForm(),
                uploadFile()
        );
    }

    public AddPetOper addPet() {
        return new AddPetOper(createReqSpec());
    }

    public DeletePetOper deletePet() {
        return new DeletePetOper(createReqSpec());
    }

    public FindPetsByStatusOper findPetsByStatus() {
        return new FindPetsByStatusOper(createReqSpec());
    }

    public FindPetsByTagsOper findPetsByTags() {
        return new FindPetsByTagsOper(createReqSpec());
    }

    public GetPetByIdOper getPetById() {
        return new GetPetByIdOper(createReqSpec());
    }

    public UpdatePetOper updatePet() {
        return new UpdatePetOper(createReqSpec());
    }

    public UpdatePetWithFormOper updatePetWithForm() {
        return new UpdatePetWithFormOper(createReqSpec());
    }

    public UploadFileOper uploadFile() {
        return new UploadFileOper(createReqSpec());
    }

    /**
     * Customize request specification
     *
     * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
     * @return api
     */
    public PetstorePetApi reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
        this.reqSpecCustomizer = reqSpecCustomizer;
        return this;
    }

    /**
     * Add a new pet to the store
     * Add a new pet to the store
     *
     * @see #body Create a new pet in the store (required)
     * return Pet
     */
    public static class AddPetOper implements Oper {

        public static final Method REQ_METHOD = POST;
        public static final String REQ_URI = "/pet";

        private RequestSpecBuilder reqSpec;
        private ResponseSpecBuilder respSpec;

        public AddPetOper(RequestSpecBuilder reqSpec) {
            this.reqSpec = reqSpec;
            reqSpec.setContentType("application/json");
            reqSpec.setAccept("application/json");
            this.respSpec = new ResponseSpecBuilder();
        }

        /**
         * POST /pet
         *
         * @param handler handler
         * @param <T>     type
         * @return type
         */
        @Override
        public <T> T execute(Function<Response, T> handler) {
            return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
        }

        /**
         * POST /pet
         *
         * @param handler handler
         * @return Pet
         */
        public Pet executeAs(Function<Response, Response> handler) {
            TypeRef<Pet> type = new TypeRef<Pet>() {
            };
            return execute(handler).as(type);
        }

        /**
         * @param pet (Pet) Create a new pet in the store (required)
         * @return operation
         */
        public AddPetOper body(Pet pet) {
            reqSpec.setBody(pet);
            return this;
        }

        /**
         * Customize request specification
         *
         * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
         * @return operation
         */
        public AddPetOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
            reqSpecCustomizer.accept(reqSpec);
            return this;
        }

        /**
         * Customize response specification
         *
         * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
         * @return operation
         */
        public AddPetOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
            respSpecCustomizer.accept(respSpec);
            return this;
        }
    }

    /**
     * Deletes a pet
     *
     * @see #petIdPath Pet id to delete (required)
     * @see #apiKeyHeader  (optional)
     */
    public static class DeletePetOper implements Oper {

        public static final Method REQ_METHOD = DELETE;
        public static final String REQ_URI = "/pet/{petId}";

        private RequestSpecBuilder reqSpec;
        private ResponseSpecBuilder respSpec;

        public DeletePetOper(RequestSpecBuilder reqSpec) {
            this.reqSpec = reqSpec;
            reqSpec.setAccept("application/json");
            this.respSpec = new ResponseSpecBuilder();
        }

        /**
         * DELETE /pet/{petId}
         *
         * @param handler handler
         * @param <T>     type
         * @return type
         */
        @Override
        public <T> T execute(Function<Response, T> handler) {
            return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
        }

        public static final String API_KEY_HEADER = "api_key";

        /**
         * @param apiKey (String)  (optional)
         * @return operation
         */
        public DeletePetOper apiKeyHeader(String apiKey) {
            reqSpec.addHeader(API_KEY_HEADER, apiKey);
            return this;
        }

        public static final String PET_ID_PATH = "petId";

        /**
         * @param petId (Long) Pet id to delete (required)
         * @return operation
         */
        public DeletePetOper petIdPath(Object petId) {
            reqSpec.addPathParam(PET_ID_PATH, petId);
            return this;
        }

        /**
         * Customize request specification
         *
         * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
         * @return operation
         */
        public DeletePetOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
            reqSpecCustomizer.accept(reqSpec);
            return this;
        }

        /**
         * Customize response specification
         *
         * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
         * @return operation
         */
        public DeletePetOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
            respSpecCustomizer.accept(respSpec);
            return this;
        }
    }

    /**
     * Finds Pets by status
     * Multiple status values can be provided with comma separated strings
     *
     * @see #statusQuery Status values that need to be considered for filter (optional, default to available)
     * return List&lt;Pet&gt;
     */
    public static class FindPetsByStatusOper implements Oper {

        public static final Method REQ_METHOD = GET;
        public static final String REQ_URI = "/pet/findByStatus";

        private RequestSpecBuilder reqSpec;
        private ResponseSpecBuilder respSpec;

        public FindPetsByStatusOper(RequestSpecBuilder reqSpec) {
            this.reqSpec = reqSpec;
            reqSpec.setAccept("application/json");
            this.respSpec = new ResponseSpecBuilder();
        }

        /**
         * GET /pet/findByStatus
         *
         * @param handler handler
         * @param <T>     type
         * @return type
         */
        @Override
        public <T> T execute(Function<Response, T> handler) {
            return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
        }

        /**
         * GET /pet/findByStatus
         *
         * @param handler handler
         * @return List&lt;Pet&gt;
         */
        public List<Pet> executeAs(Function<Response, Response> handler) {
            TypeRef<List<Pet>> type = new TypeRef<List<Pet>>() {
            };
            return execute(handler).as(type);
        }

        public static final String STATUS_QUERY = "status";

        /**
         * @param status (String) Status values that need to be considered for filter (optional, default to available)
         * @return operation
         */
        public FindPetsByStatusOper statusQuery(Object... status) {
            reqSpec.addQueryParam(STATUS_QUERY, status);
            return this;
        }

        /**
         * Customize request specification
         *
         * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
         * @return operation
         */
        public FindPetsByStatusOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
            reqSpecCustomizer.accept(reqSpec);
            return this;
        }

        /**
         * Customize response specification
         *
         * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
         * @return operation
         */
        public FindPetsByStatusOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
            respSpecCustomizer.accept(respSpec);
            return this;
        }
    }

    /**
     * Finds Pets by tags
     * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     *
     * @see #tagsQuery Tags to filter by (optional)
     * return List&lt;Pet&gt;
     */
    public static class FindPetsByTagsOper implements Oper {

        public static final Method REQ_METHOD = GET;
        public static final String REQ_URI = "/pet/findByTags";

        private RequestSpecBuilder reqSpec;
        private ResponseSpecBuilder respSpec;

        public FindPetsByTagsOper(RequestSpecBuilder reqSpec) {
            this.reqSpec = reqSpec;
            reqSpec.setAccept("application/json");
            this.respSpec = new ResponseSpecBuilder();
        }

        /**
         * GET /pet/findByTags
         *
         * @param handler handler
         * @param <T>     type
         * @return type
         */
        @Override
        public <T> T execute(Function<Response, T> handler) {
            return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
        }

        /**
         * GET /pet/findByTags
         *
         * @param handler handler
         * @return List&lt;Pet&gt;
         */
        public List<Pet> executeAs(Function<Response, Response> handler) {
            TypeRef<List<Pet>> type = new TypeRef<List<Pet>>() {
            };
            return execute(handler).as(type);
        }

        public static final String TAGS_QUERY = "tags";

        /**
         * @param tags (List&lt;String&gt;) Tags to filter by (optional)
         * @return operation
         */
        public FindPetsByTagsOper tagsQuery(Object... tags) {
            reqSpec.addQueryParam(TAGS_QUERY, tags);
            return this;
        }

        /**
         * Customize request specification
         *
         * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
         * @return operation
         */
        public FindPetsByTagsOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
            reqSpecCustomizer.accept(reqSpec);
            return this;
        }

        /**
         * Customize response specification
         *
         * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
         * @return operation
         */
        public FindPetsByTagsOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
            respSpecCustomizer.accept(respSpec);
            return this;
        }
    }

    /**
     * Find pet by ID
     * Returns a single pet
     *
     * @see #petIdPath ID of pet to return (required)
     * return Pet
     */
    public static class GetPetByIdOper implements Oper {

        public static final Method REQ_METHOD = GET;
        public static final String REQ_URI = "/pet/{petId}";

        private RequestSpecBuilder reqSpec;
        private ResponseSpecBuilder respSpec;

        public GetPetByIdOper(RequestSpecBuilder reqSpec) {
            this.reqSpec = reqSpec;
            reqSpec.setAccept("application/json");
            this.respSpec = new ResponseSpecBuilder();
        }

        /**
         * GET /pet/{petId}
         *
         * @param handler handler
         * @param <T>     type
         * @return type
         */
        @Override
        public <T> T execute(Function<Response, T> handler) {
            return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
        }

        /**
         * GET /pet/{petId}
         *
         * @param handler handler
         * @return Pet
         */
        public Pet executeAs(Function<Response, Response> handler) {
            TypeRef<Pet> type = new TypeRef<Pet>() {
            };
            return execute(handler).as(type);
        }

        public static final String PET_ID_PATH = "petId";

        /**
         * @param petId (Long) ID of pet to return (required)
         * @return operation
         */
        public GetPetByIdOper petIdPath(Object petId) {
            reqSpec.addPathParam(PET_ID_PATH, petId);
            return this;
        }

        /**
         * Customize request specification
         *
         * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
         * @return operation
         */
        public GetPetByIdOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
            reqSpecCustomizer.accept(reqSpec);
            return this;
        }

        /**
         * Customize response specification
         *
         * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
         * @return operation
         */
        public GetPetByIdOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
            respSpecCustomizer.accept(respSpec);
            return this;
        }
    }

    /**
     * Update an existing pet
     * Update an existing pet by Id
     *
     * @see #body Update an existent pet in the store (required)
     * return Pet
     */
    public static class UpdatePetOper implements Oper {

        public static final Method REQ_METHOD = PUT;
        public static final String REQ_URI = "/pet";

        private RequestSpecBuilder reqSpec;
        private ResponseSpecBuilder respSpec;

        public UpdatePetOper(RequestSpecBuilder reqSpec) {
            this.reqSpec = reqSpec;
            reqSpec.setContentType("application/json");
            reqSpec.setAccept("application/json");
            this.respSpec = new ResponseSpecBuilder();
        }

        /**
         * PUT /pet
         *
         * @param handler handler
         * @param <T>     type
         * @return type
         */
        @Override
        public <T> T execute(Function<Response, T> handler) {
            return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
        }

        /**
         * PUT /pet
         *
         * @param handler handler
         * @return Pet
         */
        public Pet executeAs(Function<Response, Response> handler) {
            TypeRef<Pet> type = new TypeRef<Pet>() {
            };
            return execute(handler).as(type);
        }

        /**
         * @param pet (Pet) Update an existent pet in the store (required)
         * @return operation
         */
        public UpdatePetOper body(Pet pet) {
            reqSpec.setBody(pet);
            return this;
        }

        /**
         * Customize request specification
         *
         * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
         * @return operation
         */
        public UpdatePetOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
            reqSpecCustomizer.accept(reqSpec);
            return this;
        }

        /**
         * Customize response specification
         *
         * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
         * @return operation
         */
        public UpdatePetOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
            respSpecCustomizer.accept(respSpec);
            return this;
        }
    }

    /**
     * Updates a pet in the store with form data
     *
     * @see #petIdPath ID of pet that needs to be updated (required)
     * @see #nameQuery Name of pet that needs to be updated (optional)
     * @see #statusQuery Status of pet that needs to be updated (optional)
     */
    public static class UpdatePetWithFormOper implements Oper {

        public static final Method REQ_METHOD = POST;
        public static final String REQ_URI = "/pet/{petId}";

        private RequestSpecBuilder reqSpec;
        private ResponseSpecBuilder respSpec;

        public UpdatePetWithFormOper(RequestSpecBuilder reqSpec) {
            this.reqSpec = reqSpec;
            reqSpec.setAccept("application/json");
            this.respSpec = new ResponseSpecBuilder();
        }

        /**
         * POST /pet/{petId}
         *
         * @param handler handler
         * @param <T>     type
         * @return type
         */
        @Override
        public <T> T execute(Function<Response, T> handler) {
            return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
        }

        public static final String PET_ID_PATH = "petId";

        /**
         * @param petId (Long) ID of pet that needs to be updated (required)
         * @return operation
         */
        public UpdatePetWithFormOper petIdPath(Object petId) {
            reqSpec.addPathParam(PET_ID_PATH, petId);
            return this;
        }

        public static final String NAME_QUERY = "name";

        /**
         * @param name (String) Name of pet that needs to be updated (optional)
         * @return operation
         */
        public UpdatePetWithFormOper nameQuery(Object... name) {
            reqSpec.addQueryParam(NAME_QUERY, name);
            return this;
        }

        public static final String STATUS_QUERY = "status";

        /**
         * @param status (String) Status of pet that needs to be updated (optional)
         * @return operation
         */
        public UpdatePetWithFormOper statusQuery(Object... status) {
            reqSpec.addQueryParam(STATUS_QUERY, status);
            return this;
        }

        /**
         * Customize request specification
         *
         * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
         * @return operation
         */
        public UpdatePetWithFormOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
            reqSpecCustomizer.accept(reqSpec);
            return this;
        }

        /**
         * Customize response specification
         *
         * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
         * @return operation
         */
        public UpdatePetWithFormOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
            respSpecCustomizer.accept(respSpec);
            return this;
        }
    }

    /**
     * uploads an image
     *
     * @see #petIdPath ID of pet to update (required)
     * @see #additionalMetadataQuery Additional Metadata (optional)
     * @see #body  (optional)
     * return ModelApiResponse
     */
    public static class UploadFileOper implements Oper {

        public static final Method REQ_METHOD = POST;
        public static final String REQ_URI = "/pet/{petId}/uploadImage";

        private RequestSpecBuilder reqSpec;
        private ResponseSpecBuilder respSpec;

        public UploadFileOper(RequestSpecBuilder reqSpec) {
            this.reqSpec = reqSpec;
            reqSpec.setContentType("application/octet-stream");
            reqSpec.setAccept("application/json");
            this.respSpec = new ResponseSpecBuilder();
        }

        /**
         * POST /pet/{petId}/uploadImage
         *
         * @param handler handler
         * @param <T>     type
         * @return type
         */
        @Override
        public <T> T execute(Function<Response, T> handler) {
            return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
        }

        /**
         * POST /pet/{petId}/uploadImage
         *
         * @param handler handler
         * @return ModelApiResponse
         */
        public ModelApiResponse executeAs(Function<Response, Response> handler) {
            TypeRef<ModelApiResponse> type = new TypeRef<ModelApiResponse>() {
            };
            return execute(handler).as(type);
        }

        /**
         * @param body (File)  (optional)
         * @return operation
         */
        public UploadFileOper body(File body) {
            reqSpec.setBody(body);
            return this;
        }

        public static final String PET_ID_PATH = "petId";

        /**
         * @param petId (Long) ID of pet to update (required)
         * @return operation
         */
        public UploadFileOper petIdPath(Object petId) {
            reqSpec.addPathParam(PET_ID_PATH, petId);
            return this;
        }

        public static final String ADDITIONAL_METADATA_QUERY = "additionalMetadata";

        /**
         * @param additionalMetadata (String) Additional Metadata (optional)
         * @return operation
         */
        public UploadFileOper additionalMetadataQuery(Object... additionalMetadata) {
            reqSpec.addQueryParam(ADDITIONAL_METADATA_QUERY, additionalMetadata);
            return this;
        }

        /**
         * Customize request specification
         *
         * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
         * @return operation
         */
        public UploadFileOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
            reqSpecCustomizer.accept(reqSpec);
            return this;
        }

        /**
         * Customize response specification
         *
         * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
         * @return operation
         */
        public UploadFileOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
            respSpecCustomizer.accept(respSpec);
            return this;
        }
    }
}
