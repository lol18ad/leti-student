#!/bin/sh
set -eu
ROOT_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
DIST="$ROOT_DIR/.gradle-bootstrap/gradle-8.9/bin/gradle"
if [ ! -x "$DIST" ]; then
  mkdir -p "$ROOT_DIR/.gradle-bootstrap"
  ARCHIVE="$ROOT_DIR/.gradle-bootstrap/gradle-8.9-bin.zip"
  if [ ! -f "$ARCHIVE" ]; then
    if command -v curl >/dev/null 2>&1; then curl -fL --retry 3 -o "$ARCHIVE" https://services.gradle.org/distributions/gradle-8.9-bin.zip
    elif command -v wget >/dev/null 2>&1; then wget -O "$ARCHIVE" https://services.gradle.org/distributions/gradle-8.9-bin.zip
    else echo 'curl or wget is required' >&2; exit 1; fi
  fi
  command -v unzip >/dev/null 2>&1 || { echo 'unzip is required' >&2; exit 1; }
  unzip -q "$ARCHIVE" -d "$ROOT_DIR/.gradle-bootstrap"
fi
exec "$DIST" "$@"
