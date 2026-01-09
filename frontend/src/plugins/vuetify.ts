import "vuetify/styles";
import { createVuetify } from "vuetify";
import { aliases, mdi } from "vuetify/iconsets/mdi";
import {
  VApp,
  VContainer,
  VCard,
  VCardTitle,
  VCardText,
  VCardActions,
  VBtn,
  VAlert,
  VForm,
  VTextField,
  VTextarea,
  VSpacer,
  VDataTable,
  VIcon,
  VChip,
  VSkeletonLoader,
} from "vuetify/components";

export const vuetify = createVuetify({
  components: {
    VApp,
    VContainer,
    VCard,
    VCardTitle,
    VCardText,
    VCardActions,
    VBtn,
    VAlert,
    VForm,
    VTextField,
    VTextarea,
    VSpacer,
    VDataTable,
    VIcon,
    VChip,
    VSkeletonLoader,
  },
  icons: {
    defaultSet: "mdi",
    aliases,
    sets: { mdi },
  },
});
