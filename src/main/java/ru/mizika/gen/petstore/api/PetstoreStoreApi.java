package ru.mizika.gen.petstore.api;

import ru.mizika.gen.petstore.model.Order;

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

public class PetstoreStoreApi {

private Supplier<RequestSpecBuilder> reqSpecSupplier;
    private Consumer<RequestSpecBuilder> reqSpecCustomizer;

        private PetstoreStoreApi(Supplier<RequestSpecBuilder> reqSpecSupplier) {
            this.reqSpecSupplier = reqSpecSupplier;
            }

            public static PetstoreStoreApi store(Supplier<RequestSpecBuilder> reqSpecSupplier) {
                return new PetstoreStoreApi(reqSpecSupplier);
                }

                private RequestSpecBuilder createReqSpec() {
                RequestSpecBuilder reqSpec = reqSpecSupplier.get();
                if(reqSpecCustomizer != null) {
                reqSpecCustomizer.accept(reqSpec);
                }
                return reqSpec;
                }

                public List<Oper> getAllOperations() {
                    return Arrays.asList(
                            deleteOrder(),
                            getInventory(),
                            getOrderById(),
                            placeOrder()
                    );
                    }

                            public DeleteOrderOper deleteOrder() {
                            return new DeleteOrderOper(createReqSpec());
                            }

                            public GetInventoryOper getInventory() {
                            return new GetInventoryOper(createReqSpec());
                            }

                            public GetOrderByIdOper getOrderById() {
                            return new GetOrderByIdOper(createReqSpec());
                            }

                            public PlaceOrderOper placeOrder() {
                            return new PlaceOrderOper(createReqSpec());
                            }

                    /**
                    * Customize request specification
                    * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
                    * @return api
                    */
                    public PetstoreStoreApi reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
                        this.reqSpecCustomizer = reqSpecCustomizer;
                        return this;
                        }

                                /**
                                * Delete purchase order by ID
                                * For valid response try integer IDs with value &lt; 1000. Anything above 1000 or nonintegers will generate API errors
                                *
                                    * @see #orderIdPath ID of the order that needs to be deleted (required)
                                */
                                public static class DeleteOrderOper implements Oper {

                                public static final Method REQ_METHOD = DELETE;
                                public static final String REQ_URI = "/store/order/{orderId}";

                                private RequestSpecBuilder reqSpec;
                                private ResponseSpecBuilder respSpec;

                                public DeleteOrderOper(RequestSpecBuilder reqSpec) {
                                this.reqSpec = reqSpec;
                                        reqSpec.setAccept("application/json");
                                this.respSpec = new ResponseSpecBuilder();
                                }

                                /**
                                * DELETE /store/order/{orderId}
                                * @param handler handler
                                * @param <T> type
                                * @return type
                                */
                                @Override
                                public <T> T execute(Function<Response, T> handler) {
                                return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
                                }

                                    public static final String ORDER_ID_PATH = "orderId";

                                    /**
                                    * @param orderId (Long) ID of the order that needs to be deleted (required)
                                    * @return operation
                                    */
                                    public DeleteOrderOper orderIdPath(Object orderId) {
                                    reqSpec.addPathParam(ORDER_ID_PATH, orderId);
                                    return this;
                                    }

                                /**
                                * Customize request specification
                                * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
                                * @return operation
                                */
                                public DeleteOrderOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
                                reqSpecCustomizer.accept(reqSpec);
                                return this;
                                }

                                /**
                                * Customize response specification
                                * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
                                * @return operation
                                */
                                public DeleteOrderOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
                                respSpecCustomizer.accept(respSpec);
                                return this;
                                }
                                }
                                /**
                                * Returns pet inventories by status
                                * Returns a map of status codes to quantities
                                *
                                    * return Map&lt;String, Integer&gt;
                                */
                                public static class GetInventoryOper implements Oper {

                                public static final Method REQ_METHOD = GET;
                                public static final String REQ_URI = "/store/inventory";

                                private RequestSpecBuilder reqSpec;
                                private ResponseSpecBuilder respSpec;

                                public GetInventoryOper(RequestSpecBuilder reqSpec) {
                                this.reqSpec = reqSpec;
                                        reqSpec.setAccept("application/json");
                                this.respSpec = new ResponseSpecBuilder();
                                }

                                /**
                                * GET /store/inventory
                                * @param handler handler
                                * @param <T> type
                                * @return type
                                */
                                @Override
                                public <T> T execute(Function<Response, T> handler) {
                                return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
                                }

                                    /**
                                    * GET /store/inventory
                                    * @param handler handler
                                    * @return Map&lt;String, Integer&gt;
                                    */
                                    public Map<String, Integer> executeAs(Function<Response, Response> handler) {
                                    TypeRef<Map<String, Integer>> type = new TypeRef<Map<String, Integer>>(){};
                                return execute(handler).as(type);
                                    }

                                /**
                                * Customize request specification
                                * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
                                * @return operation
                                */
                                public GetInventoryOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
                                reqSpecCustomizer.accept(reqSpec);
                                return this;
                                }

                                /**
                                * Customize response specification
                                * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
                                * @return operation
                                */
                                public GetInventoryOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
                                respSpecCustomizer.accept(respSpec);
                                return this;
                                }
                                }
                                /**
                                * Find purchase order by ID
                                * For valid response try integer IDs with value &lt;&#x3D; 5 or &gt; 10. Other values will generate exceptions.
                                *
                                    * @see #orderIdPath ID of order that needs to be fetched (required)
                                    * return Order
                                */
                                public static class GetOrderByIdOper implements Oper {

                                public static final Method REQ_METHOD = GET;
                                public static final String REQ_URI = "/store/order/{orderId}";

                                private RequestSpecBuilder reqSpec;
                                private ResponseSpecBuilder respSpec;

                                public GetOrderByIdOper(RequestSpecBuilder reqSpec) {
                                this.reqSpec = reqSpec;
                                        reqSpec.setAccept("application/json");
                                this.respSpec = new ResponseSpecBuilder();
                                }

                                /**
                                * GET /store/order/{orderId}
                                * @param handler handler
                                * @param <T> type
                                * @return type
                                */
                                @Override
                                public <T> T execute(Function<Response, T> handler) {
                                return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
                                }

                                    /**
                                    * GET /store/order/{orderId}
                                    * @param handler handler
                                    * @return Order
                                    */
                                    public Order executeAs(Function<Response, Response> handler) {
                                    TypeRef<Order> type = new TypeRef<Order>(){};
                                return execute(handler).as(type);
                                    }

                                    public static final String ORDER_ID_PATH = "orderId";

                                    /**
                                    * @param orderId (Long) ID of order that needs to be fetched (required)
                                    * @return operation
                                    */
                                    public GetOrderByIdOper orderIdPath(Object orderId) {
                                    reqSpec.addPathParam(ORDER_ID_PATH, orderId);
                                    return this;
                                    }

                                /**
                                * Customize request specification
                                * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
                                * @return operation
                                */
                                public GetOrderByIdOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
                                reqSpecCustomizer.accept(reqSpec);
                                return this;
                                }

                                /**
                                * Customize response specification
                                * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
                                * @return operation
                                */
                                public GetOrderByIdOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
                                respSpecCustomizer.accept(respSpec);
                                return this;
                                }
                                }
                                /**
                                * Place an order for a pet
                                * Place a new order in the store
                                *
                                    * @see #body  (optional)
                                    * return Order
                                */
                                public static class PlaceOrderOper implements Oper {

                                public static final Method REQ_METHOD = POST;
                                public static final String REQ_URI = "/store/order";

                                private RequestSpecBuilder reqSpec;
                                private ResponseSpecBuilder respSpec;

                                public PlaceOrderOper(RequestSpecBuilder reqSpec) {
                                this.reqSpec = reqSpec;
                                        reqSpec.setContentType("application/json");
                                        reqSpec.setAccept("application/json");
                                this.respSpec = new ResponseSpecBuilder();
                                }

                                /**
                                * POST /store/order
                                * @param handler handler
                                * @param <T> type
                                * @return type
                                */
                                @Override
                                public <T> T execute(Function<Response, T> handler) {
                                return handler.apply(RestAssured.given().spec(reqSpec.build()).expect().spec(respSpec.build()).when().request(REQ_METHOD, REQ_URI));
                                }

                                    /**
                                    * POST /store/order
                                    * @param handler handler
                                    * @return Order
                                    */
                                    public Order executeAs(Function<Response, Response> handler) {
                                    TypeRef<Order> type = new TypeRef<Order>(){};
                                return execute(handler).as(type);
                                    }

                                    /**
                                    * @param order (Order)  (optional)
                                    * @return operation
                                    */
                                    public PlaceOrderOper body(Order order) {
                                    reqSpec.setBody(order);
                                    return this;
                                    }

                                /**
                                * Customize request specification
                                * @param reqSpecCustomizer consumer to modify the RequestSpecBuilder
                                * @return operation
                                */
                                public PlaceOrderOper reqSpec(Consumer<RequestSpecBuilder> reqSpecCustomizer) {
                                reqSpecCustomizer.accept(reqSpec);
                                return this;
                                }

                                /**
                                * Customize response specification
                                * @param respSpecCustomizer consumer to modify the ResponseSpecBuilder
                                * @return operation
                                */
                                public PlaceOrderOper respSpec(Consumer<ResponseSpecBuilder> respSpecCustomizer) {
                                respSpecCustomizer.accept(respSpec);
                                return this;
                                }
                                }
                        }
