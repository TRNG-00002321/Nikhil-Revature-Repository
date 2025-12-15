class User
{
    private Long id;
    private String email;
    private String name;
    private boolean active;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.active = true;
    }

    public User(Long id, String name, String email, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "User{id=" + id + ", email='" + email + "', name='" + name + "', active=" + active + "}";
    }
}
