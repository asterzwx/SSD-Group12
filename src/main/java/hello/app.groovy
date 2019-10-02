@RestController
class ThisWillActuallyRun {

    @RequestMapping("/home")
    String home() {
        return "Hello World!"
    }

}