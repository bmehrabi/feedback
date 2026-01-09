import { describe, it, expect } from "vitest";
import { api } from "../axios";

describe("axios client", () => {
  it("should have correct base URL", () => {
    expect(api.defaults.baseURL).toBe("http://localhost:8080");
  });
});