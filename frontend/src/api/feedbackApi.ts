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

export interface FeedbackCreateDTO {
  message: string;
  author?: string;
}

export const createFeedback = async (
  dto: FeedbackCreateDTO
) => {
  const res = await api.post("/feedback", dto);
  return res.data;
};

export const markFeedbackHelpful = async (
  id: string
): Promise<FeedbackDTO> => {
  const res = await api.put(`/feedback/${id}/helpful`);
  return res.data;
};