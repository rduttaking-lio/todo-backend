package ci

def buildMavenPackage(boolean skipTests = true) {
    def cmd = skipTests ? 'mvn clean package -DskipTests' : 'mvn clean package'
    echo "Running: ${cmd}"
    sh cmd
}