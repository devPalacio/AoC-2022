with open('src/day3.txt') as f:
    input = f.readlines()

inputStripped = [line.strip() for line in input]

compartmented = []
for line in inputStripped:
    length = int(len(line) / 2)
    frontHalf = line[:length]
    backHalf = line[length:]
    compartmented.append((frontHalf, backHalf))

setOfBadges = []
for half in compartmented:
    front = set(half[0])
    back = set(half[1])
    setOfBadges.append(front.intersection(back))


def letterToPriority(letter):
    if ord(letter) > 97:
        return ord(letter) - 96
    else:
        return ord(letter) - 38


# Part 1
print(sum([letterToPriority(letter.pop()) for letter in setOfBadges]))

everyThree = [inputStripped[i:i+3] for i in range(0, len(inputStripped), 3)]

everyThreeSet = [list(map(set, each)) for each in everyThree]

badgeLetter = []
for elf in everyThreeSet:
    badgeLetter.append(elf[0].intersection(elf[1], elf[2]))

# Part 2
print(sum([letterToPriority(letter.pop()) for letter in badgeLetter]))
