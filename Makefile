sonarlint:
	@which sonarlint || brew install sonarlint
	@cp ./scripts/git-hooks/pre-commit .git/hooks/pre-commit && chmod +x .git/hooks/pre-commit
	@cp ./scripts/git-hooks/pre-push .git/hooks/pre-push && chmod +x .git/hooks/pre-push

config:
	@cp application.yml ./src/main/resources/
