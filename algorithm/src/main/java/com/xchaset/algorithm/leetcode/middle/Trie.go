package middle

import (
	"fmt"
	"strings"
)

func main() {
	trie := Trie{}
	trie.Insert("辉致医药/IT/IT")
	search := trie.Search("辉致医药/IT/IT")
	fmt.Println(search)
	trie.Insert("辉致医药/部门1")
	trie.Insert("辉致医药/部门2")
	trie.Insert("辉致医药/部门3")
	trie.Insert("辉致医药/部门4")
	fmt.Println(trie.Search("辉致医药/部门2"))
	fmt.Println(trie.Search("辉致医药/部门5"))
	parent, b := trie.FindParent("辉致医药/IT/IT")
	fmt.Println(b)
	fmt.Println(parent.DepartmentName)
}

type Trie struct {
	DepartmentId   int64
	DepartmentName string
	Childs         map[string]*Trie
}

var deptIdIncrement int64 = 1

func (t *Trie) Insert(level string) {
	split := strings.Split(level, "/")
	node := t
	for _, k := range split {
		if node.Childs == nil {
			node.Childs = make(map[string]*Trie)
		}
		_, ok := node.Childs[k]
		if !ok {
			node.Childs[k] = &Trie{DepartmentId: deptIdIncrement, DepartmentName: k}
			deptIdIncrement++
		}
		node = node.Childs[k]
	}
}

func (t *Trie) Search(level string) bool {
	split := strings.Split(level, "/")
	node := t
	for _, v := range split {
		trie, ok := node.Childs[v]
		if !ok {
			return false
		}
		node = trie
	}
	return true
}

func (t *Trie) FindParent(level string) (*Trie, bool) {
	split := strings.Split(level, "/")
	join := make([]string, 0)
	for i := 0; i < len(split)-1; i++ {
		join = append(join, split[i])
	}
	node := t
	for _, v := range join {
		trie, ok := node.Childs[v]
		if !ok {
			return nil, false
		}
		node = trie
	}
	return node, true
}
