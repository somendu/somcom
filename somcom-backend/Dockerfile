FROM gradle:8.10.2-jdk21-alpine AS build

WORKDIR /workspace
COPY . .
RUN gradle clean installDist --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /workspace/build/install/shopping-cart-backend /app

ENV PORT=8080
EXPOSE 8080

CMD ["/app/bin/shopping-cart-backend"]
