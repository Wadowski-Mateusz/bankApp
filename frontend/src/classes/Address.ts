export default class Address {
  private _country: string;
  private _sector: string;
  private _city: string;
  private _street: string;
  private _number: string;
  private _zip: string;

  constructor(
    country: string,
    sector: string,
    city: string,
    street: string,
    number: string,
    zip: string,
  ) {
    this._country = country;
    this._sector = sector;
    this._city = city;
    this._street = street;
    this._number = number;
    this._zip = zip;
  }

  public get country(): string {
    return this._country;
  }

  public set country(value: string) {
    this._country = value;
  }

  public get sector(): string {
    return this._sector;
  }

  public set sector(value: string) {
    this._sector = value;
  }

  public get city(): string {
    return this._city;
  }

  public set city(value: string) {
    this._city = value;
  }

  public get street(): string {
    return this._street;
  }

  public set street(value: string) {
    this._street = value;
  }

  public get number(): string {
    return this._number;
  }

  public set number(value: string) {
    this._number = value;
  }

  public get zip(): string {
    return this._zip;
  }

  public set zip(value: string) {
    this._zip = value;
  }
}
