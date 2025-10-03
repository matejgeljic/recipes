export interface SpringBootPagination<T> {
  content: T[]; // The actual data items for the current page
  pageable: {
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    };
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
  };
  last: boolean; // Whether this is the last page
  totalElements: number; // Total number of items across all pages
  totalPages: number; // Total number of pages
  size: number; // Page size (items per page)
  number: number; // Current page number (zero-based)
  sort: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  };
  first: boolean; // Whether this is the first page
  numberOfElements: number; // Number of items in the current page
  empty: boolean; // Whether the current page has no items
}

export interface CreateRecipeRequestDto {
  name: string;
  description?: string;
  ingredients: CreateIngredientRequestDto[];
  instructions: string[];
  preparationTime?: number;
  servings?: number;
  dishType?: DishType;
  dietaryInformation?: DietaryInformation;
  status: RecipeStatus;
}

//TODO change when fixed on server
export interface CreateRecipeResponseDto {
  id: string;
  name: string;
  description: string;
  ingredients: CreateIngredientResponseDto[];
  instructions: string[];
  preparationTime: number; // Duration in milliseconds
  servings: number;
  dishType: string;
  dietaryInformation: string;
  status: string;
  createdAt: Date;
  updatedAt: Date;
}

//TODO change when fixed on server
export interface UpdateRecipeResponseDto {
  id: string;
  name: string;
  description: string;
  ingredients: UpdateIngredientResponseDto[];
  instructions: string[];
  preparationTime: number; // Duration in milliseconds
  servings: number;
  dishType: string;
  dietaryInformation: string;
  status: string;
  createdAt: Date;
  updatedAt: Date;
}

export interface UpdateRecipeRequestDto {
  id: string;
  name: string;
  description?: string;
  ingredients: UpdateIngredientRequestDto[];
  instructions: string[];
  preparationTime?: number;
  servings?: number;
  dishType?: DishType;
  dietaryInformation?: DietaryInformation;
  status: RecipeStatus;
}

export interface GetRecipeDetailsResponseDto {
  id: string;
  name: string;
  description: string;
  ingredients: GetRecipeDetailsIngredientResponseDto[];
  instructions: string[];
  preparationTime: number;
  servings: number;
  dishType: DishType;
  dietaryInformation: DietaryInformation;
  status: RecipeStatus;
  publisher: PublisherDto;
  comments: GetCommentResponseDto[];
  averageRating: number;
  totalRatings: number;
  currentUserRating: number;
  createdAt: Date;
  updatedAt: Date;
}

export interface GetRecipeSummaryResponseDto {
  id: string;
  name: string;
  dishType: DishType;
  status: RecipeStatus;
  publisher: PublisherDto;
}

export enum DishType {
  DESSERT = "DESSERT",
  MAIN_DISH = "MAIN_DISH",
  PASTRIES = "PASTRIES",
  WARM_APPETIZER = "WARM_APPETIZER",
  SIDE_DISHES_AND_STEWS = "SIDE_DISHES_AND_STEWS",
  COLD_APPETIZER = "COLD_APPETIZER",
  SALAD = "SALAD",
  SOUP = "SOUP",
  DRINK = "DRINK",
  PRESERVED_FOOD = "PRESERVED_FOOD",
  SAUCES_DIPS_AND_SALAD_DRESSING = "SAUCES_DIPS_AND_SALAD_DRESSING",
  OTHER = "OTHER",
}

export enum DietaryInformation {
  NONE = "NONE",
  VEGETARIAN = "VEGETARIAN",
  VEGAN = "VEGAN",
  GLUTEN_FREE = "GLUTEN_FREE",
  DAIRY_FREE = "DAIRY_FREE",
  NUT_FREE = "NUT_FREE",
  SOY_FREE = "SOY_FREE",
  PALEO = "PALEO",
  KETO = "KETO",
  LOW_CARB = "LOW_CARB",
  LOW_FAT = "LOW_FAT",
  LOW_CALORIE = "LOW_CALORIE",
  ORGANIC = "ORGANIC",
}

export enum RecipeStatus {
  DRAFT = "DRAFT",
  PUBLISHED = "PUBLISHED",
}

export interface CreateIngredientRequestDto {
  name: string;
  quantity: number;
  unit: Unit;
}

export enum Unit {
  GRAMS = "GRAMS",
  KILOGRAMS = "KILOGRAMS",
  MILLILITERS = "MILLILITERS",
  LITERS = "LITERS",
  TEASPOONS = "TEASPOONS",
  TABLESPOONS = "TABLESPOONS",
  PIECES = "PIECES",
}

export interface CreateIngredientResponseDto {
  id: string;
  name: string;
  quantity: number;
  unit: Unit;
  createdAt: Date;
  updatedAt: Date;
}

export interface UpdateIngredientResponseDto {
  id: string;
  name: string;
  quantity: number;
  unit: Unit;
  createdAt: Date;
  updatedAt: Date;
}

export interface UpdateIngredientRequestDto {
  id: string;
  name: string;
  quantity: number;
  unit: Unit;
}

export interface GetRecipeDetailsIngredientResponseDto {
  id: string;
  name: string;
  quantity: number;
  unit: Unit;
  createdAt: Date;
  updatedAt: Date;
}

export interface PublisherDto {
  id: string;
  name: string;
  email: string;
}

export interface GetCommentResponseDto {
  id: string;
  content: string;
  userId: string;
  name: string;
  createdAt: Date;
}

export interface CreateCommentResponseDto {
  id: string;
  content: string;
  createdAt: Date;
}

export interface CreateCommentRequestDto {
  content: string;
}

export interface CreateRatingResponseDto {
  id: string;
  value: number;
  userId: string;
  username: string;
  recipeId: string;
  createdAt: Date;
  updatedAt: Date;
}

export interface CreateRatingRequestDto {
  value: number;
}
