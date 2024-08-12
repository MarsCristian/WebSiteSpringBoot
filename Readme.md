# R1: CRUD de médicos (requer login de administrador)
- http://localhost:8080/medicos/listar
- http://localhost:8080/medicos/cadastrar
### Create [x]
### Read [x]
### Update [x]
### Delete [x]
### Login [x]

# R2: CRUD de pacientes (requer login de administrador)
- http://localhost:8080/pacientes/listar
- http://localhost:8080/pacientes/cadastrar
### Create [x]
### Read [x]
### Update [x]
### Delete [x]
### Login [x]

# R3 e R4: Listagem de todos os médicos e por especialidade (sem login)
- http://localhost:8080/medicos/listar
### Todos [x]
### Especialidade [x]

# R5: Agendamento de Consuta (Paciente->agenda)
- http://localhost:8080/login
- http://localhost:8080/consultas/listar
### Consulta [x]

# R6: Listar consultas de um paciente (paciente logado)
- http://localhost:8080/login
- http://localhost:8080/consultas/listar
### Consulta [x]

# R7: Mesmo horario de consultas
- http://localhost:8080/consultas/cadastrar
### Horario 30 ou 00 []
### Horario funciona [x]

# R8: Listar todas consultas do medico (login medico)
- http://localhost:8080/login
- http://localhost:8080/consultas/listar
### Consulta [x]

# R9: Internacionalizar [x]