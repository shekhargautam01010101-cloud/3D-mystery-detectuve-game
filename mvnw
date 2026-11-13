#!/bin/bash
set -e
if ! command -v mvn >/dev/null 2>&1; then
  echo "Maven is required but was not found in PATH."
  exit 1
fi
mvn "$@"
