# Shopping Cart Backend

Java 21 backend for a shopping cart and checkout flow using Vert.x for routing and request handling. This project is designed to be deployable on Google Cloud Run.

## Stack

- Java 21
- Gradle
- Vert.x Web
- In-memory storage by default
- Mock payment gateway for end-to-end flow testing

## Current structure

- `ApiVerticle` hosts the HTTP API
- `AppRoutes` centralizes route registration
- handlers manage request/response flow
- services contain business logic
- `OptionalWorkerVerticle` is kept as a placeholder for future background work inside the same deployable service

## APIs

### `GET /health`
Health check endpoint.

### `GET /api/products`
Returns sample fashion products with available sizes and pricing.

### `POST /api/orders`
Creates an order after validating:

- `productId`
- `productSize`
- `productQuantity`
- `productAmount`

Example request:

```json
{
  "productId": "fashion-hoodie-001",
  "productSize": "M",
  "productQuantity": 2,
  "productAmount": 259800,
  "currency": "INR"
}
```

Amounts are in the smallest currency unit. `259800` means INR 2598.00.

### `GET /api/orders/:orderId`
Returns order details and current payment status.

### `POST /api/payments/checkout`
Creates a payment session for an order.

Example request:

```json
{
  "orderId": "order_xxx"
}
```

Example response:

```json
{
  "orderId": "order_xxx",
  "paymentId": "payment_xxx",
  "paymentUrl": "http://localhost:8080/api/payments/mock-gateway/payment_xxx",
  "provider": "mock",
  "orderStatus": "PENDING_PAYMENT",
  "paymentStatus": "PENDING"
}
```

### `GET /api/payments/mock-gateway/:paymentId`
Simulated hosted payment page for local testing.

### `GET /api/payments/callback`
Payment redirect callback from the gateway.

### `POST /api/payments/webhook`
Server-to-server webhook endpoint for a real payment gateway. This currently supports a mock event shape so the API contract is ready for a real integration.

## Frontend flow

1. Load products from `GET /api/products`
2. User chooses a product, size, and quantity
3. Create the order with `POST /api/orders`
4. Start checkout with `POST /api/payments/checkout`
5. Redirect the user to `paymentUrl`
6. Payment gateway sends the user back to `/api/payments/callback`
7. Backend redirects the user to the frontend success or checkout screen
8. Frontend verifies final status with `GET /api/orders/:orderId`

## Environment variables

- `PORT` default `8080`
- `APP_BASE_URL` default `http://localhost:8080`
- `FRONTEND_BASE_URL` default `http://localhost:3000`
- `FRONTEND_SUCCESS_PATH` default `/order-success`
- `FRONTEND_CANCEL_PATH` default `/checkout`
- `DEFAULT_CURRENCY` default `INR`
- `PAYMENT_PROVIDER` default `mock`

## Run locally

```bash
gradle run
```

## Build

```bash
gradle clean build
```

## Deploy to Google Cloud Run

```bash
gcloud config set project YOUR_PROJECT_ID
gcloud builds submit --tag gcr.io/YOUR_PROJECT_ID/shopping-cart-backend
gcloud run deploy shopping-cart-backend \
  --image gcr.io/YOUR_PROJECT_ID/shopping-cart-backend \
  --platform managed \
  --region asia-south1 \
  --allow-unauthenticated \
  --set-env-vars APP_BASE_URL=https://YOUR_CLOUD_RUN_URL,FRONTEND_BASE_URL=https://YOUR_FRONTEND_URL,PAYMENT_PROVIDER=mock
```

## Notes

- The backend recalculates the total amount and does not trust the frontend price.
- Cloud Run is stateless, so persistent order storage should move to Firestore or Cloud SQL in the next step.
- The payment abstraction is intentionally simple so you can plug in Stripe, Razorpay, or another gateway later.
