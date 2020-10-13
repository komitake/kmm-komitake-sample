import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}

extension RepositoryData: Identifiable {}

struct ContentView: View {
    let gitHubApi = GitHubApi(token: ConstKt.GITHUB_API_TOKEN)
    @State var repositoryList: [RepositoryData] = []
    var body: some View {
        NavigationView {
            List(repositoryList) { repo in
                VStack(alignment: .leading) {
                    Text(repo.name).font(.title)
                    Text(repo.defaultBranch)
                }
            }
            .navigationBarTitle(greet())
            .onAppear(perform: {
                ApiDataStore(api: self.gitHubApi).getRepos { (list, error) in
                    if let list = list {
                        self.repositoryList = list
                    }
                }
            })
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(repositoryList: [
            RepositoryData(id: 0, nodeId: "0", name: "abc", fullName: "ABC", private: false, htmlUrl: "http://localhost", defaultBranch: "main", description: nil, fork: false, owner: nil)
        ])
    }
}
