<script setup lang="ts">
import FeedbackForm from "./components/feedbackForm.vue";
import FeedbackTable from "./components/FeedbackTable.vue";
</script>
<template>
  <header>
    <h1>Simple Feedback Module</h1>
  </header>

  <main>
    <FeedbackTable />
    <FeedbackForm />
  </main>
</template>
