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
    template : '<div>hi</div>',
    data() {
        return {
            persons: [{"id":1, "name": "Adam", "age":20}, {"id":2, "name": "Jack", "age":25}]
        }
    },
    mounted() {
        persons = this.persons;
    }
});

var router = new VueRouter({
    routes: [
        {path: '/', component: List}
    ]
});

new Vue({
    router
}).$mount('#app');