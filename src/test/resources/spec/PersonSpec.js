describe('Persons', function () {

    describe('check find utilities are functions', function () {
        it('find person and key should be a function' , function () {
            expect(typeof findPerson).toBe('function');
            expect(typeof findPersonKey).toBe('function');
        });
    });

    describe('person list should contain values', function () {
        it('should contain two values', function () {
            expect(persons.length).toBe(2);
        });
        it('should contain given person name', function () {
            expect(persons[0].name).toContain('Adam');
        });
        it('should contain vue component list', function () {
            expect(typeof List).toBe('function');
        });
        it('should contain given persion details', function () {
            expect(persons[1].name).toBe('Jack');
            expect(persons[1].age).toBe(25);
        });
    });
});