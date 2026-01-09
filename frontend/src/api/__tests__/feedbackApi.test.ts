import { describe, it, expect, vi } from "vitest";
import {createFeedback, fetchFeedbacks, markFeedbackHelpful} from "../feedbackApi";
import { api } from "../axios";

describe("feedbackApi", () => {
  it("fetchFeedbacks should fetch feedbacks from API", async () => {
    const mockData = [
      {
        id: "1",
        message: "Nice",
        author: "Alice",
        helpfulCount: 2,
      },
    ];

    vi.spyOn(api, "get").mockResolvedValue({
      data: mockData,
    } as any);

    const result = await fetchFeedbacks();

    expect(api.get).toHaveBeenCalledWith("/feedback");
    expect(result).toEqual(mockData);
  });

  it("createFeedback should post feedback", async () => {
    const dto = {
      message: "Great",
      author: "Bob",
    };

    const response = {
      id: "2",
      message: "Great",
      author: "Bob",
      helpfulCount: 0,
    };

    vi.spyOn(api, "post").mockResolvedValue({
      data: response,
    } as any);

    const result = await createFeedback(dto);

    expect(api.post).toHaveBeenCalledWith("/feedback", dto);
    expect(result).toEqual(response);
  });

  it("markFeedbackHelpful should call PUT and return updated feedback", async () => {
    const id = "123";

    const updatedFeedback = {
      id,
      message: "Nice",
      author: "Alice",
      helpfulCount: 3,
    };

    vi.spyOn(api, "put").mockResolvedValue({
      data: updatedFeedback,
    } as any);

    const result = await markFeedbackHelpful(id);

    expect(api.put).toHaveBeenCalledWith(`/feedback/${id}/helpful`);
    expect(result).toEqual(updatedFeedback);
  });
});
