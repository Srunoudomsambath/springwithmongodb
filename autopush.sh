#!/bin/bash

# Go to your project folder (adjust path if needed)
cd "$(dirname "$0")"

# Add all changes
git add .

# Ask user for commit message
echo "Enter commit message (or leave empty to skip commit):"
read commit_message

if [ -z "$commit_message" ]; then
    echo "No commit message entered. Skipping commit."
else
    git commit -m "$commit_message"
fi

# Push to main branch
git push origin main

echo "✅ Auto pushed at $(date +'%Y-%m-%d %H:%M:%S')"
