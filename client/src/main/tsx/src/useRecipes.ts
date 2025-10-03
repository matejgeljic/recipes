import { httpClient } from "./rest/HttpClient.ts";
import { useQuery } from "@tanstack/react-query";
import type {
  GetRecipeSummaryResponseDto,
  SpringBootPagination,
} from "./rest/domain.ts";

export function useRecipesLoader() {
  return useQuery({
    queryKey: ["recipes"],
    queryFn: async () => {
      return await httpClient.get<
        SpringBootPagination<GetRecipeSummaryResponseDto>
      >("/api/v1/recipes", { withAuth: false });
    },
  });
}
