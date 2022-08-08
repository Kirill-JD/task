<template>

</template>

<script>
    export default {
        props: ['animals'],
        data() {
            return {
                animal: null
            }
        },
        methods: {
            editMethod(animal) {
                this.animal = animal
            }
        }
    }
</script>

<style>

</style>