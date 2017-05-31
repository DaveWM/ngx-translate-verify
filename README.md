# Ngx-translate-verify

A command line script to verify [ngx-translate](https://github.com/ngx-translate/core) translation files.

## Usage

To install, run `npm i -g ngx-translate-verify`.

`ngx-translate-verify` takes 2 required arguments:
* The filepath to a known valid file
* The filepath to the file to verify

The translation files should be `json` files, containing a single object. This object should have keys in the format "namespace/variable" (e.g. "homePage/title"), and non-blank string values. All keys in the known valid json should be in the json being verified.

## Example

`ngx-translate-verify i18n/en.json i18n/de.json`

## License

Licensed under the [GPL V3 license](https://www.gnu.org/licenses/gpl-3.0.en.html)
