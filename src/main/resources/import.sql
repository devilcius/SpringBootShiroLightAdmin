-- admin user password: 123456
INSERT INTO sbslauser (`id`, `name`, `username`, `email`, `password`, `salt`, `description`, `enabled`) VALUES (1, 'Admin', 'admin', 'admin@acme.com', '0UKEz14whm1YcYrCT8qxrwKaqeDw6+1jr50UAlKor/M=', 'TTbCNlD+dBsH7xhcltTQtg==', 'Administrator user', 1);
INSERT INTO userrole (`id`, `role_name`, `email`) VALUES (1, 'admin', 'admin@acme.com');
INSERT INTO sbslaapplication (`id`, `name`, `description`) VALUES (1, 'SBSLA', 'Sample application with Shiro user management, Light Admin  and Spring Boot framework.');
INSERT INTO sbslafeature (`id`, `application_id`, `name`, `release_version`, `description`, `enabled`) VALUES (1, 1, 'Spring Boot', 'v1.2.4', 'Production-ready Spring applications.', 1);
INSERT INTO sbslafeature (`id`, `application_id`, `name`, `release_version`, `description`, `enabled`) VALUES (2, 1, 'Apache Shiro', 'v1.2.0.RC1', 'Apache Shiro is a powerful and easy-to-use Java security framework.', 1);
INSERT INTO sbslafeature (`id`, `application_id`, `name`, `release_version`, `description`, `enabled`) VALUES (3, 1, 'Light Admin', 'v1.2.5.RELEASE', 'Pluggable data administration UI library for Java web applications.', 1);
INSERT INTO userapplication (`application_id`, `user_id`) VALUES (1, 1);
