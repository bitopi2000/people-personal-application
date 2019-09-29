var persons = [];

function findPerson (id) {
    return persons[findPersonKey(id)];
}

function findPersonKey (id) {
    for (var key = 0; key < persons.length; key++) {
        if (persons[key].id == id) {
            return key;
        }
    }
}

var List = Vue.extend({
    template: '#person-list',
    data: function () {
        return {persons: []};
    },
    mounted() {
        personService.findAll(response => {
            this.persons = response.data;
            persons = response.data
        })
    }
});

var Person = Vue.extend({
    template: '#person',
    data: function () {
        return {person: findPerson(this.$route.params.person_id )};
    }
});

var AddPerson = Vue.extend({
    template: '#add-person',
    data() {
        return {
            person: {name: '', dateOfBirth: '', address: {street: '', city: '', zipCode: ''}}
        }
    },
    methods: {
        createPerson() {
            personService.create(this.person, response => {
                router.push('/');
            })
        }
    },
    components: {
        vuejsDatepicker
    }
});

var router = new VueRouter({
    routes: [
        {path: '/', component: List},
        {path: '/persons/:person_id', component: Person, name: 'person'},
        {path: '/add-person', component: AddPerson}
    ]
});

new Vue({
    router
}).$mount('#app');