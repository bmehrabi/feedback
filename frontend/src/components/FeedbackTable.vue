<script setup lang="ts">
import {useMutation, useQuery, useQueryClient} from "@tanstack/vue-query";
import {fetchFeedbacks, type FeedbackDTO, markFeedbackHelpful} from "@/api/feedbackApi";

const queryClient = useQueryClient();

const headers = [
  { title: "Message", key: "message" },
  { title: "Author", key: "author" },
  { title: "Helpful", key: "helpfulCount" },
  { title: "Actions", value: "actions" },
];

const { data, isLoading, isError, error } = useQuery<FeedbackDTO[]>({
  queryKey: ["feedbacks"],
  queryFn: fetchFeedbacks,
});

const markHelpfulMutation = useMutation({
  mutationFn: markFeedbackHelpful,
  onSuccess: () => {
    queryClient.invalidateQueries({ queryKey: ["feedbacks"] });
  },
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

        <!-- Actions -->
        <template #item.actions="{ item }">
          <v-btn
              icon
              color="success"
              size="small"
              :loading="markHelpfulMutation.isPending.value"
              :disabled="markHelpfulMutation.isPending.value"
              @click="markHelpfulMutation.mutate(item.id)"
          >
            <v-icon>mdi-thumb-up</v-icon>
          </v-btn>
        </template>

      </v-data-table>
    </v-card>
  </v-container>
</template>
