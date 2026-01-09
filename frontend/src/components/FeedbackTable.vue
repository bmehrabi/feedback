<script setup lang="ts">
import { useQuery } from "@tanstack/vue-query";
import { fetchFeedbacks, type FeedbackDTO } from "@/api/feedbackApi";

const headers = [
  { title: "Message", key: "message" },
  { title: "Author", key: "author" },
  { title: "Helpful", key: "helpfulCount" },
];

const { data, isLoading, isError, error } = useQuery<FeedbackDTO[]>({
  queryKey: ["feedbacks"],
  queryFn: fetchFeedbacks,
});
</script>

<template>
  <v-container>
    <v-card>
      <!-- Loading -->
      <div v-if="isLoading" class="pa-4">
        <v-skeleton-loader type="table-row@5" />
      </div>

      <!-- Error -->
      <v-alert
          v-else-if="isError"
          type="error"
          class="ma-4"
      >
        Failed to load feedback
      </v-alert>

      <v-data-table
          v-else
          :headers="headers"
          :items="data ?? []"
          :loading="isLoading"
          item-key="id"
      >
        <template #loading>
          <v-skeleton-loader type="table-row@5" />
        </template>

        <template #no-data>
          <v-alert type="info" border="start">
            No feedback available
          </v-alert>
        </template>

        <template #item.helpfulCount="{ value }">
          <v-chip color="primary" variant="outlined">
            {{ value }}
          </v-chip>
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>
