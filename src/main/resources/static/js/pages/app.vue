<template>
    <div>
<!--        <animals-list :animals="animals" />-->
    </div>
</template>

<script>
    import animalList from 'components/animals/animalList.vue'

    export default {
        components: {
            animalList
        },
        data() {
            return {
                messages: []
            }
        }
    }
</script>
</style>
