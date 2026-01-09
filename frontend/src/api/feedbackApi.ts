import { api } from "./axios";

export interface FeedbackDTO {
  id: string;
  message: string;
  author: string;
  helpfulCount: number;
}

export const fetchFeedbacks = async (): Promise<FeedbackDTO[]> => {
  const res = await api.get<FeedbackDTO[]>("/feedback");
  return res.data;
};