var personService = {
    findAll(callback) {
        axios
            .get('/api/persons')
            .then(response => callback(response))
            .catch(error => console.log(error))
    },
    create(person, callback) {
        axios
            .post('/api/persons', person)
            .then(response => callback(response))
            .catch(error => console.log(error))
    }
};