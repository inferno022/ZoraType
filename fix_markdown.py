#!/usr/bin/env python3
"""
Script to fix common markdown formatting issues
"""
import os
import re

def fix_markdown_file(filepath):
    """Fix common markdown formatting issues in a file"""
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    original_content = content
    
    # Fix missing blank lines around headings
    content = re.sub(r'(\n)(#{1,6}\s+.*?)(\n)([^#\n])', r'\1\n\2\3\n\4', content)
    content = re.sub(r'([^#\n])(\n)(#{1,6}\s+.*?)(\n)', r'\1\2\n\3\4', content)
    
    # Fix missing blank lines around fenced code blocks
    content = re.sub(r'(\n)(```)', r'\1\n\2', content)
    content = re.sub(r'(```\n)([^`])', r'\1\n\2', content)
    
    # Fix missing blank lines around lists
    content = re.sub(r'([^\n])(\n)([-*+]\s)', r'\1\2\n\3', content)
    content = re.sub(r'([-*+]\s.*?)(\n)([^\n-*+\s])', r'\1\2\n\3', content)
    
    # Ensure single trailing newline
    content = content.rstrip() + '\n'
    
    # Remove excessive blank lines (more than 2)
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    if content != original_content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Fixed: {filepath}")
        return True
    return False

def main():
    """Fix all markdown files in the current directory"""
    fixed_count = 0
    for file in os.listdir('.'):
        if file.endswith('.md'):
            if fix_markdown_file(file):
                fixed_count += 1
    
    print(f"Fixed {fixed_count} markdown files")

if __name__ == '__main__':
    main()