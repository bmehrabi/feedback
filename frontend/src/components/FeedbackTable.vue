<script setup lang="ts">
import { useQuery } from "@tanstack/vue-query";
import { fetchFeedbacks, type FeedbackDTO } from "@/api/feedbackApi";

const { data, isLoading, isError, error } = useQuery<FeedbackDTO[]>({
  queryKey: ["feedbacks"],
  queryFn: fetchFeedbacks,
});
</script>

<template>
  <div>
    <h2>Feedback List</h2>

    <div v-if="isLoading">Loading...</div>
    <div v-else-if="isError">
      Error: {{ (error as Error).message }}
    </div>

    <table v-else>
      <thead>
      <tr>
        <th>ID</th>
        <th>Message</th>
        <th>Author</th>
        <th>Helpful Count</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="f in data" :key="f.id">
        <td>{{ f.id }}</td>
        <td>{{ f.message }}</td>
        <td>{{ f.author }}</td>
        <td>{{ f.helpfulCount }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
