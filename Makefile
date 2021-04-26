.PHONY: run
run:
	make build
	docker-compose up --build -d

.PHONY: build
build:
	./gradlew build


.PHONY: test/all
test/all:
	./gradlew clean test
	make test/integration

.PHONY: test/unit
test/unit:
	./gradlew clean test

.PHONY: test/integration
test/integration:
	docker-compose -f docker-compose-test-it.yml up --build -d
	./gradlew integrationTest