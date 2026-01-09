import { describe, it, expect, vi } from "vitest";
import { fetchFeedbacks } from "../feedbackApi";
import { api } from "../axios";

describe("fetchFeedbacks", () => {
  it("should fetch feedbacks from API", async () => {
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
});
