<script setup lang="ts">
import GlobalHeaderNav from "@/components/GlobalHeaderNav/GlobalHeaderNav.vue"
</script>

<template>
    <GlobalHeaderNav />
    <router-view v-slot="{ Component }">
        <transition name="fade">
            <component :is="Component" />
        </transition>
    </router-view>
</template>

<style></style>
