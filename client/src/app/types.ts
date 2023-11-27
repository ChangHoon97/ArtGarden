export interface Perform_Info {
  id: string;
  name: string;
  startDate: string;
  endDate: string;
  place: string;
  price: string;
  posterUrl: string;
}

export interface Review_Info {
  id: number;
  perform_id: string;
  content: string;
  rate: number;
  member_id: number;
  created_at: string;
  modified_at: string;
}
