## Samfa CMS

# Installation
- import database.sql and insert.sql
- log-in with the following credentials:

```
username: admin
password: admin
login as: admin
```

# Important Notes
- Reports module can only work if ONLY_FULL_GROUP_BY in sql_mode is disabled in the database server by default
- Before running the app, please disable such restriction by running this command as super admin in the database:

```
SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));
```
