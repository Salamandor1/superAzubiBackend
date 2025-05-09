# How to work with SSH Key inside a devcontainer

As the Devcontainor does not know the SSH Key that is saved in your WSL, you will have to generate a new one inside the container

inside the terminal:

- ssh-keygen -t ed25519 -C "devcontainer"
- cat ~/.ssh/id_ed25519.pub

Then, copy public key and add it to GitLab