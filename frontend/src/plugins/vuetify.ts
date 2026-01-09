import "vuetify/styles";
import { createVuetify } from "vuetify";
import { aliases, mdi } from "vuetify/iconsets/mdi";
import {VDataTable} from "vuetify/components";

export const vuetify = createVuetify({
  components: {
    VDataTable,
  },
  icons: {
    defaultSet: "mdi",
    aliases,
    sets: { mdi },
  },
});
