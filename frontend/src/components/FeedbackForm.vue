<script setup lang="ts">
import { ref } from "vue";
import { useMutation, useQueryClient } from "@tanstack/vue-query";
import { createFeedback, type FeedbackCreateDTO } from "@/api/feedbackApi";

const queryClient = useQueryClient();

const form = ref();
const valid = ref(false);

const feedback = ref<FeedbackCreateDTO>({
  message: "",
});

const rules = {
  required: (v: string) => !!v || "Required",
  min3: (v: string) => v.length >= 3 || "Min 3 characters",
};

const mutation = useMutation({
  mutationFn: createFeedback,
  onSuccess: () => {
    queryClient.invalidateQueries({ queryKey: ["feedbacks"] });
    feedback.value = { message: "", author: "" };
    form.value?.resetValidation();
  },
});
</script>

<template>
  <v-card class="mb-6">
    <v-card-title>Add Feedback</v-card-title>

    <v-card-text>
      <v-form
          ref="form"
          v-model="valid"
          lazy-validation
      >
        <v-text-field
            v-model="feedback.author"
            label="Author"
            required
        />

        <v-textarea
            v-model="feedback.message"
            label="Message"
            :rules="[rules.required, rules.min3]"
            required
        />
      </v-form>
    </v-card-text>

    <v-card-actions>
      <v-spacer />

      <v-btn
          color="primary"
          :loading="mutation.isPending.value"
          :disabled="!valid"
          @click="mutation.mutate(feedback)"
      >
        Submit
      </v-btn>
    </v-card-actions>

    <v-alert
        v-if="mutation.isError.value"
        type="error"
        class="ma-4"
    >
      Failed to submit feedback
    </v-alert>
  </v-card>
</template>
