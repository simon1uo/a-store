import { createRouter, createWebHistory } from "vue-router";
import Store from "@/views/Store/Store.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/store",
      name: "Store",
      component: Store,
    },
    {
      path: "/",
      redirect: "/store"
    }
  ],
});

export default router;
