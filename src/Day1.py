import os

with open('src/day1.txt') as f:
    listOfElvesWithSnacks = f.read().split("\n\n")

snacksByElf = [elf.splitlines() for elf in listOfElvesWithSnacks]
snacksByElfInt = [list(map(int, elf)) for elf in snacksByElf]
summedCalByElf = [sum(elf) for elf in snacksByElfInt]
summedCalByElf.sort(reverse=True)

print(max(summedCalByElf))
print(sum(summedCalByElf[:4]))
