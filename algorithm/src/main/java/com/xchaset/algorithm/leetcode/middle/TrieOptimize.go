package middle

import (
	"fmt"
	"strings"
)

func main() {
	var maxId int64 = 23
	var levelIdMap = map[string]int64{
		"辉致医药":                             23,
		"辉致医药/IT":                          2,
		"辉致医药/HR":                          3,
		"辉致医药/HR/HQ":                       4,
		"辉致医药/Communication":               5,
		"辉致医药/Communication/Communication": 6,
		"辉致医药/DHCE":                        7,
		"辉致医药/DHCE/Commercial Operations":  8,
	}
	trie := Trie{incrementDepartmentId: maxId, levelIdMap: levelIdMap}
	trie.InsertWith("辉致医药/IT/IT")
	search := trie.Search("辉致医药/IT/IT")
	fmt.Println(search)
	trie.InsertWith("辉致医药/部门1")
	trie.InsertWith("辉致医药/部门2")
	trie.InsertWith("辉致医药/部门3")
	trie.InsertWith("辉致医药/部门4")
	fmt.Println(trie.Search("辉致医药/部门2"))
	fmt.Println(trie.Search("辉致医药/部门5"))
	parent, b := trie.FindParent("辉致医药/IT/IT")
	fmt.Println(b)
	fmt.Println(parent.DepartmentName)
	fmt.Println(parent.DepartmentId)

	findParent, exi := trie.FindParent("辉致医药/IT")
	fmt.Println(exi)
	fmt.Println(findParent.DepartmentId)
	fmt.Println(findParent.DepartmentName)

	for k, v := range levelIdMap {
		fmt.Print("k:" + k + " v:")
		fmt.Println(v)
	}

	fmt.Println("==============")
	pare, exit := trie.FindParent("辉致医药/HR/HQ")
	if exit {
		fmt.Print("department:" + pare.DepartmentName + " => ")
		fmt.Println(pare.DepartmentId)
	}

}

func getParentDeptFromLevel2(departmentLevel string, departmentName string, deep int) string {
	index := strings.Split(departmentLevel, "/")
	res := make([]string, 0)
	for i, v := range index {
		if i == deep && v == departmentName {
			break
		}
		res = append(res, v)
	}
	join := strings.Join(res, "/")
	return join
}

type TWxDepartment struct {
	TenantId       int64  `gorm:"column:tenant_id;"`
	DepartmentId   int64  `gorm:"column:department_id;"`
	DepartmentName string `gorm:"column:department_name;"`
	Order          int64  `gorm:"column:order;"`
	ParentId       int64  `gorm:"column:parent_id;"`
	State          int64  `gorm:"column:state;"`
	IsSync         int64  `gorm:"column:is_sync;"`
}

type Trie struct {
	DepartmentId          int64
	DepartmentName        string
	Childs                map[string]*Trie
	incrementDepartmentId int64            // 如果是之前不存的部门，新增进来的数据这个部门需要自增
	levelIdMap            map[string]int64 // 带有层级的部门：部门id 的map,需要再根节点初始化赋值
}

var deptIdIncrement int64 = 1

func (t *Trie) InsertWith(level string) {
	if t.levelIdMap == nil {
		t.levelIdMap = make(map[string]int64)
	}
	split := strings.Split(level, "/")
	node := t
	join := make([]string, 0)
	for _, k := range split {
		join = append(join, k)
		if node.Childs == nil {
			node.Childs = make(map[string]*Trie)
		}
		_, ok := node.Childs[k]
		if !ok {
			var deptId int64 = 1
			if exId, isPresent := t.levelIdMap[strings.Join(join, "/")]; isPresent {
				deptId = exId
			} else {
				deptId = t.incrementDepartmentId
				t.levelIdMap[strings.Join(join, "/")] = deptId
				t.incrementDepartmentId++
			}
			node.Childs[k] = &Trie{DepartmentId: deptId, DepartmentName: k}
		}
		node = node.Childs[k]
	}
}
func (t *Trie) InsertWithDepartmentId(level string, departmentId int64) {
	split := strings.Split(level, "/")
	node := t
	for _, k := range split {
		if node.Childs == nil {
			node.Childs = make(map[string]*Trie)
		}
		_, ok := node.Childs[k]
		if !ok {
			var deptId int64 = 0
			if departmentId != 0 {
				deptId = departmentId
			} else {
				deptId = deptIdIncrement
				t.incrementDepartmentId++
			}
			node.Childs[k] = &Trie{DepartmentId: deptId, DepartmentName: k}
		}
		node = node.Childs[k]
	}
}

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
